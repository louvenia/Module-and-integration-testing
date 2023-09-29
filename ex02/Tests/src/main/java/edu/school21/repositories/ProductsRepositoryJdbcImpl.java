package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private final DataSource ds;

    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        String query = "select * from products";

        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()) {
            listProduct.add(new Product(rs.getLong("id"),
                                    rs.getString("name"),
                                    rs.getInt("price")));
        }

        stmt.close();
        conn.close();

        return listProduct;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        String query = "select * from products where id = " + id;

        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
        return Optional.of(new Product(rs.getLong("id"),
                                    rs.getString("name"),
                                    rs.getInt("price")));
        }

        stmt.close();
        conn.close();

        return Optional.empty();
    }

    @Override
    public void update(Product product) throws SQLException {
        String query = "update products set name = ?, price = ? where id = " + product.getId();

        Connection conn = ds.getConnection();
        PreparedStatement prst = conn.prepareStatement(query, Statement.NO_GENERATED_KEYS);
        if(product.getName() == null) {
            prst.setNull(1, Types.VARCHAR);
        } else {
            prst.setString(1, product.getName());
        }
        if(product.getPrice() == null) {
            prst.setNull(2, Types.INTEGER);
        } else {
            prst.setInt(2, product.getPrice());
        }
        prst.execute();

        prst.close();
        conn.close();
    }

    @Override
    public void save(Product product) throws SQLException {
        String query = "insert into products(id, name, price) values(?, ?, ?)";

        Connection conn = ds.getConnection();
        PreparedStatement prst = conn.prepareStatement(query, Statement.NO_GENERATED_KEYS);
        prst.setLong(1, product.getId());
        prst.setString(2, product.getName());
        prst.setInt(3, product.getPrice());
        prst.execute();

        ResultSet rs = prst.getGeneratedKeys();
        if (rs.next()) {
            product.setId(rs.getLong(1));
        }

        prst.close();
        conn.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "delete from products where id = " + id;

        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);

        stmt.close();
        conn.close();
    }
}
