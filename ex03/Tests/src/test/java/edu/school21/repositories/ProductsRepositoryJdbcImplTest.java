package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductsRepositoryJdbcImplTest {
    private EmbeddedDatabase ds;
    private ProductsRepository pr;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "bananas", 135),
            new Product(2L, "tangerines", 200),
            new Product(3L, "apples" , 95),
            new Product(4L, "pineapples", 250),
            new Product(5L, "figs", 300)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(4L, "pineapples", 250);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2L, "melon", 215);
    final Product EXPECTED_UPDATED_NULL_PRODUCT = new Product(2L, null, 0);
    final Product EXPECTED_SAVE_PRODUCT = new Product(6L, "watermelon", 220);

    @BeforeEach
    void init() {
        ds = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        pr = new ProductsRepositoryJdbcImpl(ds);
    }

    @Test
    void testFindAll()  throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, pr.findAll());
    }

    @Test
    void testFindById()  throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, pr.findById(4L).get());
    }

    @Test
    void testUpdate() throws SQLException {
        pr.update(new Product(2L, "melon", 215));
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, pr.findById(2L).get());
    }

    @Test
    void testUpdateNull() throws SQLException {
        pr.update(new Product(2L, null, null));
        Assertions.assertEquals(EXPECTED_UPDATED_NULL_PRODUCT, pr.findById(2L).get());
    }

    @Test
    void testSave() throws SQLException {
        pr.save(new Product(6L, "watermelon", 220));
        Assertions.assertEquals(EXPECTED_SAVE_PRODUCT, pr.findById(6L).get());
    }

    @Test
    void testDelete() throws SQLException {
        pr.delete(6L);
        Assertions.assertThrows(NoSuchElementException.class, () -> pr.findById(6L).get());
    }

    @AfterEach
    void close() {
        ds.shutdown();
    }
}
