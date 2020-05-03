<template>
    <v-autocomplete
            :value="value"
            @input="$emit('input', $event)"
            hint="Choose a doctor"
            :items="doctor"
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
                    There are no existing doctors
                </v-list-item-title>
            </v-list-item>
        </template>

    </v-autocomplete>
</template>

<script>

    import {mapActions, mapState} from "vuex";

    export default {
        name: "DoctorSelection",
        data: () => ({
            rules: [v => !!v || 'Doctor is required'],
        }),
        props: {
            value: null,
            disabled: {
                type: Boolean,
                value: false,
            },
        },
        computed: {
            ...mapState('doctor/doctor', ['doctor']),
        },
        methods: {
            ...mapActions('doctor/doctor', ['getDoctor']),
        },
        created() {
        	console.log("getDoctor in doctorSelection");
            this.getDoctor();
        }
    }
</script>

<style scoped>

</style>
