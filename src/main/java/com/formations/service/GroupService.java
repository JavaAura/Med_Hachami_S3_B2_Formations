package com.formations.service;

import com.formations.model.Group;

import java.util.List;

public interface GroupService {
    public boolean addGroup(Group group);
    public Group getGroupById(Long id);
    public List<Group> getAllGroups();
    public void update(Group group);
    public void delete(Group group);
}
