<template>
    <v-autocomplete
            :value="value"
            @input="$emit('input', $event)"
            hint="Choose a clinic"
            :items="clinics"
            label="Clinic"
            persistent-hint
            prepend-icon="mdi-hospital-building"
    ></v-autocomplete>
</template>

<script>
    // ****************************
    // *  OVO URADITI ASINHRONO   *
    // ****************************
    // MOGUCE PRIMENITI:
    // V-MODEL koji je izabrana klinika.
    import {mapActions, mapState} from "vuex";

    export default {
        name: "ClinicSelection",
        props: {
            value: null
        },
        computed: {
            ...mapState('clinics/readClinics', ['clinics']),
        },
        methods: {
            ...mapActions('clinics/readClinics', ['getClinics']),
        },
        created() {
            this.getClinics();
        }
    }
</script>

<style scoped>

</style>
