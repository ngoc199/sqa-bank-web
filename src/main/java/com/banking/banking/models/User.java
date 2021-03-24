package com.banking.banking.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import com.banking.banking.models.values.Status;
import com.banking.banking.models.values.UserType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {
   private static final long serialVersionUID = 1L;

   @Id
   private String id;

   @Pattern(regexp = "[a-zA-Z][a-zA-Z1-9]{5,}", message = "Tên đăng nhập phải chứa ít nhất 6 ký tự, không bắt đầu bằng số, không chứa ký tự đặc biệt")
   private String username;

   @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6}", message = "Mật khẩu chỉ chứa 6 ký tự phải bao gồm chữ và số")
   private String password;

   @NotNull
   private String name;

   @Enumerated(EnumType.STRING)
   private Status status;

   private LocalDateTime createdAt;

   @OneToMany(mappedBy = "user")
   private Set<Phone> phones;

   @OneToMany(mappedBy = "user")
   private List<Address> addresses;

   @OneToMany(mappedBy = "user")
   private List<LoginInfo> loginInfos;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "branch_id")
   private Branch branch;

   @Enumerated(EnumType.STRING)
   private UserType role;

   @PrePersist
   private void onCreate() {
      id = UUID.randomUUID().toString();
      createdAt = LocalDateTime.now();
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
   }

   @Override
   public boolean isAccountNonExpired() {
      return false;
   }

   @Override
   public boolean isAccountNonLocked() {
      return !status.equals(Status.LOCKED);
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return false;
   }

   @Override
   public boolean isEnabled() {
      return status.equals(Status.ACTIVE);
   }
}
