package mrs.eclinicapi.model; /***********************************************************************
 * Module:  Clinic.java
 * Author:  Manojlović
 * Purpose: Defines the Class Clinic
 ***********************************************************************/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private ClinicAdministrator clinicAdministrator;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private List<ClinicRoom> clinicRoom;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private List<Nurse> nurses;


/*TODO
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public List<MedicalStaff> getMedicalStaff() {
      return medicalStaff;
   }

   public void setMedicalStaff(List<MedicalStaff> medicalStaff) {
      this.medicalStaff = medicalStaff;
   }

   public void setClinicRoom(java.util.Collection<ClinicRoom> newClinicRoom) {
      removeAllClinicRoom();
      for (java.util.Iterator iter = newClinicRoom.iterator(); iter.hasNext();)
         addClinicRoom((ClinicRoom)iter.next());
   }


   public void addClinicRoom(ClinicRoom newClinicRoom) {
      if (newClinicRoom == null)
         return;
      if (this.clinicRoom == null)
         this.clinicRoom = new ArrayList<>();
      if (!this.clinicRoom.contains(newClinicRoom))
         this.clinicRoom.add(newClinicRoom);
   }

   public void removeClinicRoom(ClinicRoom oldClinicRoom) {
      if (oldClinicRoom == null)
         return;
      if (this.clinicRoom != null)
         if (this.clinicRoom.contains(oldClinicRoom))
            this.clinicRoom.remove(oldClinicRoom);
   }

   public void removeAllClinicRoom() {
      if (clinicRoom != null)
         clinicRoom.clear();
   }*/

}