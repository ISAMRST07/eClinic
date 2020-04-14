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
    private List<Clinic> clinics;

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private List<UnregistredUser> unregistredUser;


   public Collection<Clinic> getClinics() {
      if (clinics == null)
         clinics = new ArrayList<Clinic>();
      return clinics;
   }

   public java.util.Iterator getIteratorClinicAdministrator() {
      if (clinics == null)
         clinics = new ArrayList<Clinic>();
      return clinics.iterator();
   }

   public void setClinics(java.util.Collection<ClinicAdministrator> newClinicAdministrator) {
      removeAllClinicAdministrator();
      for (java.util.Iterator iter = newClinicAdministrator.iterator(); iter.hasNext();)
         addClinicAdministrator((Clinic)iter.next());
   }

   public void addClinicAdministrator(Clinic newClinicAdministrator) {
      if (newClinicAdministrator == null)
         return;
      if (this.clinics == null)
         this.clinics = new ArrayList<Clinic>();
      if (!this.clinics.contains(newClinicAdministrator))
         this.clinics.add(newClinicAdministrator);
   }

   public void removeClinicAdministrator(ClinicAdministrator oldClinicAdministrator) {
      if (oldClinicAdministrator == null)
         return;
      if (this.clinics != null)
         if (this.clinics.contains(oldClinicAdministrator))
            this.clinics.remove(oldClinicAdministrator);
   }

   public void removeAllClinicAdministrator() {
      if (clinics != null)
         clinics.clear();
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
