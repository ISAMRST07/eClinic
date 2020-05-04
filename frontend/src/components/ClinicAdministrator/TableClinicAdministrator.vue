<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="clinicAdmins"
                class="elevation-1"
        >
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>Clinic Administrators</v-toolbar-title>
                    <v-divider
                            class="mx-4"
                            inset
                            vertical
                    ></v-divider>
                    <v-spacer></v-spacer>
                </v-toolbar>
            </template>
            <template v-slot:item.remove="{ item }">
                <v-icon
                        @click="deleteDialog(item)"
                        color="red"
                >
                    mdi-delete
                </v-icon>
            </template>
            <template v-slot:item.update="{ item }">
                <v-icon
                        @click="updateDialog(item)"
                        color="amber darken-2"
                >
                    mdi-pencil
                </v-icon>
            </template>
            <template v-slot:no-data>
                <p>There are no existing clinics</p>
            </template>
        </v-data-table>
        <clinic-administrator-delete-dialog
                v-model="dialog"
                :clinic="clinicToDelete"
                @close="deleteDialog(null)"
                @delete="deleteClinic"
        />
        <modify-clinic-administrator-dialog
                mode="update"
                :clinicToUpdate="clinicToUpdate"
                v-model="editDialog"/>
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import ClinicAdministratorDeleteDialog from "./ClinicAdministratorDeleteDialog";
    import ModifyClinicAdministratorDialog from "./ModifyClinicAdministratorDialog";

    export default {
        name: "TableClinicAdministrator",
        components: {ModifyClinicAdministratorDialog, ClinicAdministratorDeleteDialog},
        data: () => ({
            editDialog: false,
            dialog: false,
            clinicToDelete: null,
            clinicToUpdate:null,
            clinicWithDescription: null,
            headers: [
                {
                    text: 'Name',
                    align: 'start',
                    value: 'name',
                },
                { text: 'Surname', value: 'surname' },
                { text: 'Email', value: 'email' },
                { text: 'Username', value: 'username' },
                { text: 'Clinic name', value: 'clinic.name' },
                { text: 'Update', value: 'update', sortable: false, align: 'center' },
                { text: 'Remove', value: 'remove', sortable: false, align: 'center' }
            ],
        }),
        computed: {
            ...mapState('clinicAdmins/readClinicAdmins', ['clinicAdmins']),
            editClinic: {
                get() {
                    return this.$store.state.clinicAdmins.readClinicAdmins.clinicAdmins;
                },
                set(val) {
                    this.$store.commit('clinicAdmins/readClinicAdmins/updateClinicAdmin', val);
                }
            }
        },
        methods: {
            ...mapActions('clinicAdmins/readClinicAdmins', ['getClinicAdmins']),
            ...mapActions('clinicAdmins/readClinicAdmins', ['deleteClinicAdminApi']),
            // ...mapActions('clinics/readClinics', ['deleteClinicApi']),
            deleteDialog(clinic) {
                this.clinicToDelete = clinic;
                this.dialog = !this.dialog;
            },
            deleteClinic() {
                this.deleteClinicAdminApi(this.clinicToDelete);
                this.deleteDialog(null);
            },
            toggleClinicDescription(clinic) {
                this.clinicWithDescription = clinic;
                this.descriptionDialog = !this.descriptionDialog;
            },
            updateDialog(clinicAdmin) {
                this.clinicToUpdate = clinicAdmin;
                this.editDialog = true;
            }

        },
        created() {
            this.getClinicAdmins();
        },
        filters: {
            descriptionShortener(val) {
                return val.slice(0, 10)
            }
        },

    }
</script>

<style scoped>

</style>
