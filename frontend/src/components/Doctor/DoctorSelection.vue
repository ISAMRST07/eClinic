<template>
    <v-autocomplete
            :value="value"
            @input="$emit('input', $event)"
            hint="Choose clinic room"
            :items="doctors"
            label="Doctor*"
            persistent-hint
            item-text="user.name"
            item-value="user.name"
            :rules="rules"
            return-object
            prepend-icon="mdi-hospital-building"
    >
        <template v-slot:no-data>
            <v-list-item>
                <v-list-item-title>
                    There are no doctors
                </v-list-item-title>
            </v-list-item>
        </template>

    </v-autocomplete>
</template>

<script>

    import {mapActions, mapState} from "vuex";
    import {ClinicalAdmin, ClinicalCenterAdmin} from "../../utils/DrawerItems";

    export default {
        name: "DoctorSelection",
        data: () => ({
            rules: [v => !!v || 'Doctor required'],
        }),
        props: {
            value: null,
            disabled: {
                type: Boolean,
                default: false,
            },
            clinicID: {
                type: String
            },
            dateTime: null
        },
        computed: {
        	...mapState('doctor/doctor', ['doctors']),
        	...mapState('auth', ['user']),
        },
        methods: {
            ...mapActions('doctor/doctor', ['getClinicDoctors', 'getDoctorsForTime']),
        },
        mounted() {
            if (this.dateTime) {
                this.getDoctorsForTime(
                    {
                        clinicID: this.clinicID,
                        pageNumber: 1,
                        pageSize: -1,
                        sort: [],
                        desc: [],
                        time: this.dateTime
                    });
            } else {
                this.getClinicDoctors({
                    clinicID: this.clinicID,
                    pageNumber: 1,
                    pageSize: -1,
                    sort: [],
                    desc: []
                });
            }
        }
    }
</script>

<style scoped>

</style>
