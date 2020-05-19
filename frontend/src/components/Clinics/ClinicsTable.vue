<template>
    <div>

        <v-card>
            <clinic-search @searched="searched" @selectedType="scheduleType = $event" @reset="reset"/>
            <v-divider></v-divider>
            <v-data-table
                    :headers="headers"
                    :items="clinics"
                    :loading="loading"
                    :options.sync="options"
                    :server-items-length="length"

                    loading-text="Building all the clinics..."
                    class="elevation-1"
            >
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
                <template v-if="role === patientCode" v-slot:item.schedule="{ item }">
                    <v-icon
                            @click="toggleScheduleDialog(item)"
                            color="lime darken-2"
                    >
                        mdi-calendar-check
                    </v-icon>
                </template>
                <template v-slot:no-data>
                    <p>There are no existing clinics</p>
                </template>
            </v-data-table>

        </v-card>
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
        <request-dialog
                v-if="role === patientCode && !!searchRequest"
                v-model="scheduleDialog"
                :clinic="scheduleClinic"
                :date="searchRequest.date"
                :type="scheduleType"
        ></request-dialog>
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import DescriptionDialog from "./DescriptionDialog";
    import DeleteDialog from "./DeleteDialog";
    import ModifyClinicDialog from "./ModifyClinicDialog";
    import {ClinicalCenterAdmin, Patient} from '../../utils/DrawerItems';
    import ClinicSearch from "./ClinicSearch";
    import RequestDialog from "./RequestDialog";

    export default {
        name: "ClinicsTable",
        components: {RequestDialog, ClinicSearch, ModifyClinicDialog, DeleteDialog, DescriptionDialog},
        data: () => ({
            loading: false,
            descriptionDialog: false,
            editDialog: false,
            dialog: false,
            clinicToDelete: null,
            clinicWithDescription: null,
            scheduleClinic: null,
            scheduleDialog: false,
            scheduleType: null,
            options: {
                page: 1,
                itemsPerPage: 10
            },
            adminCode: ClinicalCenterAdmin.code,
            patientCode: Patient.code,
            searchRequest: null,
        }),
        computed: {
            ...mapState('clinics/readClinics', ['length']),
            ...mapState('auth', ['role']),

            clinics() {
                if (this.itemsPerPage > 0)
                    return this.$store.state.clinics.readClinics.clinics.slice(0, this.options.itemsPerPage);
                else
                    return this.$store.state.clinics.readClinics.clinics;

            },
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
                let patientHeader = [
                    {text: 'Schedule', value: 'schedule', sortable: false, align: 'center'}
                ];
                let adminHeaders = [
                    {text: 'Update', value: 'update', sortable: false, align: 'center'},
                    {text: 'Remove', value: 'remove', sortable: false, align: 'center'}
                ];
                if (this.role === this.adminCode) {
                    regularHeaders = regularHeaders.concat(adminHeaders);
                } else if (this.role === this.patientCode && !!this.searchRequest)
                    regularHeaders = regularHeaders.concat(patientHeader);
                return regularHeaders;
            }
        },
        watch: {
            clinics() {
                this.loading = false;
            },
            options(val) {
                this.loading = true;
                if(!this.searchRequest)
                    this.getClinics({
                        pageNumber: val.page,
                        pageSize: val.itemsPerPage,
                        sort: val.sortBy,
                        desc: val.sortDesc
                    });
                else
                    this.search(
                        {
                            pageNumber: this.options.page,
                            pageSize: this.options.itemsPerPage,
                            sort: this.options.sortBy,
                            desc: this.options.sortDesc,
                            request: this.searchRequest
                        });

                console.log(val);
            }
        },
        methods: {
            ...mapActions('clinics/readClinics', ['getClinics']),
            ...mapActions('clinics/readClinics', ['deleteClinicApi']),
            ...mapActions('clinics/readClinics', ['search']),

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
            },
            toggleScheduleDialog(clinic) {
                this.scheduleClinic = clinic;
                this.scheduleDialog = true;
            },


            searched(payload) {
                this.searchRequest = payload;
                this.loading = true;
                this.search(
                    {
                        pageNumber: this.options.page,
                        pageSize: this.options.itemsPerPage,
                        sort: this.options.sortBy,
                        desc: this.options.sortDesc,
                        request: this.searchRequest
                    });
            },
            reset() {
                this.searchRequest = null;
                this.loading = true;
                this.getClinics({
                    pageNumber: this.options.page,
                    pageSize: this.options.itemsPerPage,
                    sort: this.options.sortBy,
                    desc: this.options.sortDesc
                });
            }

        },
        mounted() {
            this.loading = true;
            this.getClinics({
                pageNumber: this.options.page,
                pageSize: this.options.itemsPerPage,
                sort: this.options.sortBy,
                desc: this.options.sortDesc
            });
        }
    }
</script>

<style scoped>

</style>
