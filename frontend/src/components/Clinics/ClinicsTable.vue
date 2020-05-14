<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="clinics"
                :loading="loading"
                loading-text="Building all the clinics..."
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
            <template v-slot:item.description="{ item }">
                <v-icon
                        color="success"
                        @click="toggleClinicDescription(item)"
                >mdi-information
                </v-icon>
            </template>
            <template v-if="role === adminCode" v-slot:item.remove="{ item }">
                <v-icon
                        @click="deleteDialog(item)"
                        color="red"
                >
                    mdi-delete
                </v-icon>
            </template>
            <template v-if="role === adminCode" v-slot:item.update="{ item }">
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
        <delete-dialog
                v-model="dialog"
                :clinic="clinicToDelete"
                @close="deleteDialog(null)"
                @delete="deleteClinic"
        />
        <description-dialog
                v-model="descriptionDialog"
                :clinic="clinicWithDescription"
                @close="toggleClinicDescription(null)"
        />
        <modify-clinic-dialog
                mode="update"
                v-model="editDialog"/>
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import DescriptionDialog from "./DescriptionDialog";
    import DeleteDialog from "./DeleteDialog";
    import ModifyClinicDialog from "./ModifyClinicDialog";
    import {ClinicalCenterAdmin} from '../../utils/DrawerItems';

    export default {
        name: "ClinicsTable",
        components: {ModifyClinicDialog, DeleteDialog, DescriptionDialog},
        data: () => ({
            loading: false,
            descriptionDialog: false,
            editDialog: false,
            dialog: false,
            clinicToDelete: null,
            clinicWithDescription: null,
            adminCode: ClinicalCenterAdmin.code
        }),
        computed: {
            ...mapState('clinics/readClinics', ['clinics']),
            ...mapState('auth', ['role']),

            editClinic: {
                get() {
                    return this.$store.state.clinics.addClinic.clinic;
                },
                set(val) {
                    this.$store.commit('clinics/addClinic/updateClinic', val);
                }
            },

            headers() {
                let regularHeaders = [
                    {text: 'Name', align: 'start', value: 'name'},
                    {text: 'Description', value: 'description', sortable: false, align: 'center'},
                    {text: 'Address', value: 'address'}
                ];
                let adminHeaders = [
                    {text: 'Update', value: 'update', sortable: false, align: 'center'},
                    {text: 'Remove', value: 'remove', sortable: false, align: 'center'}
                ];
                if (this.role === this.adminCode) {
                    regularHeaders = regularHeaders.concat(adminHeaders);
                }
                return regularHeaders;
            }
        },
        watch: {
            clinics() {
                this.loading = false;
            }
        },
        methods: {
            ...mapActions('clinics/readClinics', ['getClinics']),
            ...mapActions('clinics/readClinics', ['deleteClinicApi']),
            deleteDialog(clinic) {
                this.clinicToDelete = clinic;
                this.dialog = !this.dialog;
            },
            deleteClinic() {
                this.deleteClinicApi(this.clinicToDelete);
                this.deleteDialog(null);
            },
            toggleClinicDescription(clinic) {
                this.clinicWithDescription = clinic;
                this.descriptionDialog = !this.descriptionDialog;
            },
            updateDialog(clinic) {
                this.editClinic = clinic;
                this.editDialog = true;
            }

        },
        created() {
            this.loading = true;
            this.getClinics();
        }

    }
</script>

<style scoped>

</style>
