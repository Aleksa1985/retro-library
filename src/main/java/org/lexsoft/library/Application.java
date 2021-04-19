package org.lexsoft.library;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jdbi.v3.core.Jdbi;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.lexsoft.library.core.exception.GlobalExceptionHandler;
import org.lexsoft.library.core.repository.AuthorBookAggregationRepository;
import org.lexsoft.library.core.repository.AuthorRepository;
import org.lexsoft.library.core.repository.BookRepository;
import org.lexsoft.library.core.service.AuthorService;
import org.lexsoft.library.core.service.BookService;
import org.lexsoft.library.core.service.impl.AuthorsServiceImpl;
import org.lexsoft.library.core.service.impl.BookServiceImpl;
import org.lexsoft.library.core.transformer.Transformer;
import org.lexsoft.library.core.transformer.impl.AuthorTransformerImpl;
import org.lexsoft.library.core.transformer.impl.BookTransformerImpl;
import org.lexsoft.library.web.controller.BookController;
import org.lexsoft.library.web.validator.AuthorValidator;
import org.lexsoft.library.web.validator.BookValidator;

public class Application extends io.dropwizard.Application<org.lexsoft.library.Configuration> {

  public static void main(final String[] args) throws Exception {
    new Application().run(args);
  }

  @Override
  public String getName() {
    return "Retro Library";
  }

  @Override
  public void initialize(final Bootstrap<org.lexsoft.library.Configuration> bootstrap) {
    bootstrap.addBundle(
        new MigrationsBundle<>() {
          @Override
          public DataSourceFactory getDataSourceFactory(
              org.lexsoft.library.Configuration configuration) {
            return configuration.getDatabaseConfiguration();
          }
        });
  }

  @Override
  public void run(
      final org.lexsoft.library.Configuration configuration, final Environment environment) {

    setupCors(environment);
    final JdbiFactory factory = new JdbiFactory();
    final Jdbi jdbi =
        factory.build(environment, configuration.getDatabaseConfiguration(), "postgresql");
    final BookRepository bookRepository = jdbi.onDemand(BookRepository.class);
    final AuthorRepository authorRepository = jdbi.onDemand(AuthorRepository.class);
    final AuthorBookAggregationRepository authorBookAggregationRepository =
        jdbi.onDemand(AuthorBookAggregationRepository.class);

    environment
        .jersey()
        .register(
            new AbstractBinder() {
              @Override
              protected void configure() {
                bind(bookRepository).to(BookRepository.class);
                bind(authorRepository).to(AuthorRepository.class);
                bind(authorBookAggregationRepository).to(AuthorBookAggregationRepository.class);
              }
            });

    AuthorValidator authorValidator = new AuthorValidator();
    BookValidator bookValidator = new BookValidator(authorValidator);

    AuthorService authorService = new AuthorsServiceImpl(authorRepository);
    BookService bookService = new BookServiceImpl(bookRepository, authorService,authorBookAggregationRepository);

    Transformer authorTransformer = new AuthorTransformerImpl();
    Transformer bookTransformer = new BookTransformerImpl(authorTransformer);

    BookController bookController = new BookController(bookService, bookValidator, bookTransformer);

    environment.jersey().register(bookController);
    environment.jersey().register(GlobalExceptionHandler.class);
  }

  private void setupCors(Environment env) {
    final FilterRegistration.Dynamic cors =
        env.servlets().addFilter("CORS", CrossOriginFilter.class);
    cors.setInitParameter("allowedOrigins", "*");
    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());
  }
}
