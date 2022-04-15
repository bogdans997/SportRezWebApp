package com.example.demo.model.dto;

public class UserReportDTO {
    private Integer roleId;
    private Long count;

    public UserReportDTO() {
    }

    public UserReportDTO(Integer roleId, Long count) {
        this.roleId = roleId;
        this.count = count;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
