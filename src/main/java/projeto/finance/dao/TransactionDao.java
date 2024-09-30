package projeto.finance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDao {
    private final JdbcTemplate jdbcTemplate;

    public TransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAllTransactions() {
        String sql = "SELECT * FROM transactions"; // Adjust table name as needed
        return jdbcTemplate.queryForList(sql);
    }

    public void addTransaction(String description, double amount) {
        String sql = "INSERT INTO transactions (description, amount) VALUES (?, ?)";
        jdbcTemplate.update(sql, description, amount);
    }

    public void updateTransaction(Long id, String description, double amount) {
        String sql = "UPDATE transactions SET description = ?, amount = ? WHERE id = ?";
        jdbcTemplate.update(sql, description, amount, id);
    }

    public void deleteTransaction(Long id) {
        String sql = "DELETE FROM transactions WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Map<String, Object> getTransactionById(Long id) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }
}