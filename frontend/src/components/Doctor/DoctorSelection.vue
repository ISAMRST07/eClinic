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
                value: false,
            },
        },
        computed: {
        	...mapState('doctor/doctor', ['doctors']),
        	...mapState('auth', ['user']),
            ...mapState('auth', ['clinic']),
        },
        methods: {
            ...mapActions('doctor/doctor', ['getDoctor']),
            ...mapActions('doctor/doctor', ['getClinicDoctor']),
        },
        created() {
           console.log("doctorselection created");
           switch (this.user.type) {
                case ClinicalCenterAdmin.code:
                    console.log("user = ClinicalCenterAdmin")
                    this.getDoctor();
                    break;
                case ClinicalAdmin.code:
                    console.log("user = ClinicalAdmin id = " + this.clinic.id);
                    this.getClinicDoctor(this.clinic.id);
                    break;
                default:
           }
        }
    }
</script>

<style scoped>

</style>
