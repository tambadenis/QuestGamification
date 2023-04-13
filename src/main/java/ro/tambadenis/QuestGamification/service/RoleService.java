package ro.tambadenis.QuestGamification.service;

import ro.tambadenis.QuestGamification.model.Role;

public interface RoleService {
    Role createRole(Role role);
    Role getRoleById(Long roleId);
    void deleteRoleById(Long roleId);
}
