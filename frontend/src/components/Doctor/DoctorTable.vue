<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="doctor"
                class="elevation-1"
                :loading="loading"
                loading-text="Contacting all the doctors to see if they still work here..."
        >
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>Doctors</v-toolbar-title>
                    <v-divider
                            class="mx-4"
                            inset
                            vertical
                    ></v-divider>
                    <v-spacer></v-spacer>
                </v-toolbar>
            </template>
            <template v-slot:item.update="{ item }">
                <v-icon
                        @click="updateDialog(item)"
                        color="amber darken-2"
                >
                    mdi-pencil
                </v-icon>
            </template>
            <template v-slot:item.remove="{ item }">
                <v-icon
                        @click="deleteDialog(item)"
                        color="red"
                >
                    mdi-delete
                </v-icon>
            </template>

            <template v-slot:no-data>
                <p>There are no doctors</p>
            </template>
        </v-data-table>
        <delete-dialog
                v-model="dialog"
                :doctor="doctorToDelete"
                @close="deleteDialog(null)"
                @delete="deleteDoctor"
        />
        <modify-doctor-dialog
                mode="update"
                :edit-doctor="editDoctor"
                v-model="editDialog"/>
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import DeleteDialog from "./DeleteDialog";
    import ModifyDoctorDialog from "./ModifyDoctorDialog";
    import {ClinicalAdmin, ClinicalCenterAdmin} from "../../utils/DrawerItems";

    export default {
        name: "DoctorTable",
        components: {DeleteDialog, ModifyDoctorDialog},
        data: () => ({
            loading: false,
            descriptionDialog: false,
            editDialog: false,
            dialog: false,
            doctorToDelete: null,
            editDoctor: null,
            headers: [
                {text: 'Name', align: 'start', value: 'user.name'},
                {text: 'Surname', align: 'center', value: 'user.surname'},
                {text: 'Email', align: 'center', value: 'user.email'},
                {text: 'Phone number', align: 'center', value: 'user.phoneNumber'},
                {text: 'Address', align: 'center', value: 'user.address'},
                {text: 'Position', align: 'center', value: 'position'},
                {text: 'Update', value: 'update', sortable: false, align: 'center'},
                {text: 'Remove', sortable: false, value: 'remove'},
            ],
        }),
        computed: {
            ...mapState('doctor/doctor', ['doctor']),
            ...mapState('auth', ['user']),
            ...mapState('auth', ['clinic']),
        },
        methods: {
            ...mapActions('doctor/doctor', ['getDoctor']),
            ...mapActions('doctor/doctor', ['getClinicDoctor']),
            ...mapActions('doctor/doctor', ['deleteDoctorApi']),

            deleteDialog(doctorToDelete) {
                this.doctorToDelete = doctorToDelete;
                this.dialog = !this.dialog;
            },
            deleteDoctor() {
                this.deleteDoctorApi(this.doctorToDelete);
                this.deleteDialog(null);
            },
            updateDialog(doctor) {
                this.$router.push(`/profile/${doctor.user.id}`)
                // console.log("updateDialog id = ");
                // console.log(doctor)
                // this.editDoctor = {
                //     id: doctor.id,
                //     email: doctor.user.email,
                //     name: doctor.user.name,
                //     surname: doctor.user.surname,
                //     phone: doctor.user.phoneNumber,
                //     address: doctor.user.address,
                //     city: doctor.user.city,
                //     country: doctor.user.country,
                //     jmbg: doctor.user.personalID,
                //     position: doctor.position
                // };
                // this.editDialog = true;
            }
        },
        created() {
            this.loading = true;
            console.log(this.user)
            switch (this.user.type) {
                case ClinicalCenterAdmin.code:
                    console.log("user = ClinicalCenterAdmin")
                    this.getDoctor();	//svi doktori
                    break;
                case ClinicalAdmin.code:
                    console.log("user = ClinicalAdmin id = " + this.clinic.id);
                    this.getClinicDoctor(this.clinic.id);  //doktori samo za clinic.id
                    break;
                default:
            }
        },
        watch: {
            doctor() {
                this.loading = false;
            }
        }
    }
</script>

<style scoped>

</style>
