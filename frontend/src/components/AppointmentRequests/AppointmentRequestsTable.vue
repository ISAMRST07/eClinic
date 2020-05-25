<template>
    <div>

        <v-card>

            <v-data-table
                    :headers="headers"
                    :items="requests"
                    class="elevation-1"
                    :loading="loading"
                    :options.sync="options"
                    :server-items-length="length"
                    loading-text="Where are my requestsssss..."
            >

                <template v-slot:item.cancel="{ item }">
                    <v-icon
                            @click="cancel(item)"
                            color="red"
                    >
                        mdi-table-cancel
                    </v-icon>
                </template>

                <template v-slot:item.deny="{ item }">
                    <v-icon
                            @click="deny(item)"
                            color="primary darken-2"
                    >
                        mdi-table-cancel
                    </v-icon>
                </template>
                <template v-slot:item.approve="{ item }">
                    <v-icon
                            @click="approve(item)"
                            color="amber darken-2"
                    >
                        mdi-check
                    </v-icon>
                </template>
                <template v-slot:no-data>
                    <p>There aren't any doctors here :(</p>
                </template>
            </v-data-table>
        </v-card>

    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import DeleteDialog from "./DeleteDialog";
    import ModifyDoctorDialog from "./ModifyDoctorDialog";
    import {ClinicalAdmin, ClinicalCenterAdmin, Patient} from "../../utils/DrawerItems";
    import DoctorSearch from "./DoctorSearch";
    import DialogAvailable from "./DialogAvailable";
    import ScheduleDialog from "./ScheduleDialog";

    export default {
        name: "DoctorTable",
        components: {ScheduleDialog, DialogAvailable, DoctorSearch, DeleteDialog, ModifyDoctorDialog},
        data: () => ({
        	search: "",
            loading: false,
            dialog: false,
            dialogAvailable: false,
            doctorToDelete: null,
            doctorAvailability: null,
            options: {
                page: 1,
                itemsPerPage: 10
            },
            adminCode: ClinicalAdmin.code,
            patientCode: Patient.code,
            searchRequest: null,
            fromClinic: false,
            scheduleType: null,
            scheduleDate: null,
            scheduleDialog: false,
            doctorToSchedule: null,
            scheduleClinic: null,
        }),
        computed: {
            ...mapState('doctor/doctor', ['length']),
            ...mapState('auth', ['token']),
            ...mapState('auth', ['role']),
            requests() {
                if (this.itemsPerPage > 0)
                    return this.$store.state.appointmentRequests.requests.slice(0, this.options.itemsPerPage);
                else
                    return this.$store.state.appointmentRequests.requests;
            },
            headers() {
                let regularHeaders = [
                    {text: 'Id', align: 'start', value: 'id'},
                    {text: 'Appointment date', align: 'center', value: 'dateTime'},
                    {text: 'Date of creation', align: 'center', value: 'dateOfCreation'},
                ];
                let patientHeader = [
                    {text: 'Cancel', value: 'cancel', sortable: false, align: 'center'},
                ];
                let adminHeaders = [
                    {text: 'Deny', value: 'deny', sortable: false, align: 'center'},
                    {text: 'Approve', value: 'approve', sortable: false, align: 'center'}
                ];
                if (this.role === this.adminCode) {
                    regularHeaders = regularHeaders.concat(adminHeaders);
                } else if (this.role === this.patientCode && !!this.searchRequest)
                    regularHeaders = regularHeaders.concat(patientHeader);
                return regularHeaders;
            }
        },
        methods: {
            ...mapActions('doctor/doctor', ['getDoctor']),
            ...mapActions('doctor/doctor', ['getClinicDoctors']),
            ...mapActions('doctor/doctor', ['deleteDoctorApi']),
            ...mapActions('doctor/doctor', ['searchApi']),

            deleteDialog(doctorToDelete) {
                this.doctorToDelete = doctorToDelete;
                this.dialog = !this.dialog;
            },
            availableDialog(doctor) {
                this.doctorAvailability = doctor;
                this.dialogAvailable = !this.dialogAvailable;
            },
            deleteDoctor() {
                this.deleteDoctorApi(this.doctorToDelete);
                this.deleteDialog(null);
            },
            toggleScheduleDialog(doctor) {
                this.doctorToSchedule = doctor;
                this.scheduleDialog = !this.scheduleDialog;
            },
            toProfile(doctor) {
                this.$router.push(`/profile/${doctor.userID}`)
            },
            populate() {
                if(!this.searchRequest)
                    this.getClinicDoctors({
                        clinicID: this.$route.params.clinicID,
                        pageNumber: this.options.page,
                        pageSize: this.options.itemsPerPage,
                        sort: this.options.sortBy,
                        desc: this.options.sortDesc
                    });
                else
                    this.searchApi(
                        {
                            pageNumber: this.options.page,
                            pageSize: this.options.itemsPerPage,
                            sort: this.options.sortBy,
                            desc: this.options.sortDesc,
                            request: this.searchRequest
                        });
            },
            searched(payload) {
                this.scheduleDate = payload.date;
                console.log(this.scheduleDate);
                this.searchRequest = payload;
                this.searchRequest.clinicID = this.$route.params.clinicID;
                this.loading = true;
                this.populate();
            },
            reset() {
                this.searchRequest = null;
                this.loading = true;
                this.populate();
            },
            async setup() {
                try {
                    let {data: clinic} = await this.$axios.get(`/api/clinic/${this.$route.params.clinicID}`,
                        {headers: {"Authorization": 'Bearer ' + this.token}});
                    this.scheduleClinic = clinic;
                    if (this.$route.params.payload) {
                        let payload = this.$route.params.payload;
                        this.scheduleType = payload.interventionType;
                        this.scheduleDate = payload.date;
                        this.searchRequest = {
                            clinicID: payload.clinicID,
                            interventionTypeID: payload.interventionType.id,
                            date: payload.date
                        };
                        this.fromClinic = true;
                    } else this.fromClinic = false;
                    this.populate();
                } catch(err) {
                    console.log("SJEBO SI");
                }
            }
        },
        mounted() {
            this.loading = true;
            this.setup();
        },
        watch: {
            doctors() {
                this.loading = false;
                if(this.options.itemsPerPage <= this.length && val.length < this.options.itemsPerPage) {
                    this.loading = true;
                    this.populate();
                }
            },
            options(val) {
                this.loading = true;
                if(!this.searchRequest)
                    this.getClinicDoctors({
                        clinicID: this.$route.params.clinicID,
                        pageNumber: val.page,
                        pageSize: val.itemsPerPage,
                        sort: val.sortBy,
                        desc: val.sortDesc
                    });
                else
                    this.searchApi(
                        {
                            pageNumber: val.page,
                            pageSize: val.itemsPerPage,
                            sort: val.sortBy,
                            desc: val.sortDesc,
                            request: this.searchRequest
                        });
            },
        }
    }
</script>

<style scoped>

</style>
