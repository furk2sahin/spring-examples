package springsecurity.demo.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import springsecurity.demo.business.abstracts.RoleService;
import springsecurity.demo.entitites.concretes.Role;
import springsecurity.demo.repository.RoleRepository;

import java.util.List;

@Service
public class RoleManager implements RoleService {

    private RoleRepository roleRepository;
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public RoleManager(RoleRepository roleRepository, JdbcTemplate jdbcTemplate) {
        this.roleRepository = roleRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> getAll() {
        
        return roleRepository.findAll();
    }

    @Override
    public List<Role> getRolesByUserId(Long userId){
        String sql = "select * from roles where user_id=?";
        return jdbcTemplate.query(
                sql, new Object[]{userId},
                (rs, rowNum) ->  new Role(rs.getLong("id"), rs.getString("authority")));
    }

    @Override
    public void deleteRolesByUserId(Long userId) {
        String sql = "Delete from roles where user_id = ?";
        jdbcTemplate.update(sql, new Object[]{userId});
    }
}
