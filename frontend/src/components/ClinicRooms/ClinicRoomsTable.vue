<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="clinicRooms"
                class="elevation-1"
        >
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>Clinics</v-toolbar-title>
                    <v-divider
                            class="mx-4"
                            inset
                            vertical
                    ></v-divider>
                    <v-spacer></v-spacer>
                </v-toolbar>
            </template>

            <template v-slot:no-data>
                <p>There are no existing clinics</p>
            </template>
        </v-data-table>
<!--        <delete-dialog-->
<!--                v-model="dialog"-->
<!--                :clinic="clinicToDelete"-->
<!--                @close="deleteDialog(null)"-->
<!--                @delete="deleteClinic"-->
<!--        />-->
<!--        <description-dialog-->
<!--                v-model="descriptionDialog"-->
<!--                :clinic="clinicWithDescription"-->
<!--                @close="toggleClinicDescription(null)"-->
<!--        />-->
<!--        <modify-clinic-dialog-->
<!--                mode="update"-->
<!--                v-model="editDialog"/>-->
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";

    export default {
        name: "ClinicRoomsTable",
        data: () => ({
            descriptionDialog: false,
            editDialog: false,
            dialog: false,
            clinicToDelete: null,
            clinicWithDescription: null,
            headers: [
                { text: 'ID', align: 'start', value: 'id' },
                { text: 'Name', value: 'name', align: 'center'},
                { text: 'Clinic ID', value: 'clinicID' },
            ],
        }),
        computed: {
            ...mapState('clinicRooms/clinicRooms', ['clinicRooms']),
            // editClinic: {
            //     get() {
            //         return this.$store.state.clinics.addClinic.clinic;
            //     },
            //     set(val) {
            //         this.$store.commit('clinics/addClinic/updateClinic', val);
            //     }
            // }
        },
        methods: {
            ...mapActions('clinicRooms/clinicRooms', ['getClinicRooms']),
            // ...mapActions('clinics/readClinics', ['deleteClinicApi']),
            // deleteDialog(clinic) {
            //     this.clinicToDelete = clinic;
            //     this.dialog = !this.dialog;
            // },
            // deleteClinic() {
            //     this.deleteClinicApi(this.clinicToDelete);
            //     this.deleteDialog(null);
            // },
            // toggleClinicDescription(clinic) {
            //     this.clinicWithDescription = clinic;
            //     this.descriptionDialog = !this.descriptionDialog;
            // },
            // updateDialog(clinic) {
            //     this.editClinic = clinic;
            //     this.editDialog = true;
            // }

        },
        created() {
            this.getClinicRooms();
        }
    }
</script>

<style scoped>

</style>
