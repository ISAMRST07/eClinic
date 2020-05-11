<template>
    <v-autocomplete
            :value="value"
            @input="$emit('input', $event)"
            hint="Choose clinic room"
            :items="clinicRooms"
            label="Clinic room*"
            persistent-hint
            item-text="name"
            item-value="name"
            :rules="rules"
            return-object
            prepend-icon="mdi-hospital-building"
    >
        <template v-slot:no-data>
            <v-list-item>
                <v-list-item-title>
                    There are no clinic rooms
                </v-list-item-title>
            </v-list-item>
        </template>

    </v-autocomplete>
</template>

<script>
   
    import {mapActions, mapState} from "vuex";
    import {ClinicalAdmin, ClinicalCenterAdmin} from "../../utils/DrawerItems";

    export default {
        name: "ClinicRoomSelection",
        data: () => ({
            rules: [v => !!v || 'Clinic room is required'],
        }),
        props: {
            value: null,
            disabled: {
                type: Boolean,
                value: false,
            },
        },
        computed: {
            ...mapState('clinicRooms/clinicRooms', ['clinicRooms']),
            ...mapState('auth', ['user']),
        },
        methods: {
            ...mapActions('clinicRooms/clinicRooms', ['getClinicRooms']),
            ...mapActions('clinicRooms/clinicRooms', ['getOneClinicRooms']),
        },
        created() {
           console.log("clinicroomselection created");
           switch (this.user.type) {
                case ClinicalCenterAdmin.code:
                	console.log("ClinicalCenterAdmin type");
                    this.getClinicRooms();
                    break;
                case ClinicalAdmin.code:
                	console.log("ClinicalAdmin type");
                    this.getOneClinicRooms(this.user);
                    break;
                default:
            }
        }
    }
</script>

<style scoped>

</style>
