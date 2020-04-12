package mrs.eclinicapi.model; /***********************************************************************
 * Module:  ClinicalCentarAdministrator.java
 * Author:  Manojlović
 * Purpose: Defines the Class ClinicalCentarAdministrator
 ***********************************************************************/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class ClinicalCentarAdministrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private List<Codebook> codebook;

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private List<ClinicAdministrator> clinicAdministrator;

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private List<UnregistredUser> unregistredUser;


   public Collection<ClinicAdministrator> getClinicAdministrator() {
      if (clinicAdministrator == null)
         clinicAdministrator = new ArrayList<>();
      return clinicAdministrator;
   }

   public java.util.Iterator getIteratorClinicAdministrator() {
      if (clinicAdministrator == null)
         clinicAdministrator = new ArrayList<>();
      return clinicAdministrator.iterator();
   }

   public void setClinicAdministrator(java.util.Collection<ClinicAdministrator> newClinicAdministrator) {
      removeAllClinicAdministrator();
      for (java.util.Iterator iter = newClinicAdministrator.iterator(); iter.hasNext();)
         addClinicAdministrator((ClinicAdministrator)iter.next());
   }

   public void addClinicAdministrator(ClinicAdministrator newClinicAdministrator) {
      if (newClinicAdministrator == null)
         return;
      if (this.clinicAdministrator == null)
         this.clinicAdministrator = new ArrayList<>();
      if (!this.clinicAdministrator.contains(newClinicAdministrator))
         this.clinicAdministrator.add(newClinicAdministrator);
   }

   public void removeClinicAdministrator(ClinicAdministrator oldClinicAdministrator) {
      if (oldClinicAdministrator == null)
         return;
      if (this.clinicAdministrator != null)
         if (this.clinicAdministrator.contains(oldClinicAdministrator))
            this.clinicAdministrator.remove(oldClinicAdministrator);
   }

   public void removeAllClinicAdministrator() {
      if (clinicAdministrator != null)
         clinicAdministrator.clear();
   }

   public Collection<UnregistredUser> getUnregistredUser() {
      if (unregistredUser == null)
         unregistredUser = new ArrayList<>();
      return unregistredUser;
   }

   public java.util.Iterator getIteratorUnregistredUser() {
      if (unregistredUser == null)
         unregistredUser = new ArrayList<>();
      return unregistredUser.iterator();
   }

   public void setUnregistredUser(java.util.Collection<UnregistredUser> newUnregistredUser) {
      removeAllUnregistredUser();
      for (java.util.Iterator iter = newUnregistredUser.iterator(); iter.hasNext();)
         addUnregistredUser((UnregistredUser)iter.next());
   }

   public void addUnregistredUser(UnregistredUser newUnregistredUser) {
      if (newUnregistredUser == null)
         return;
      if (this.unregistredUser == null)
         this.unregistredUser = new ArrayList<>();
      if (!this.unregistredUser.contains(newUnregistredUser))
         this.unregistredUser.add(newUnregistredUser);
   }

   public void removeUnregistredUser(UnregistredUser oldUnregistredUser) {
      if (oldUnregistredUser == null)
         return;
      if (this.unregistredUser != null)
         if (this.unregistredUser.contains(oldUnregistredUser))
            this.unregistredUser.remove(oldUnregistredUser);
   }

   public void removeAllUnregistredUser() {
      if (unregistredUser != null)
         unregistredUser.clear();
   }

}
