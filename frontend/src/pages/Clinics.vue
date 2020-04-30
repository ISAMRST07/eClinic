<template>
    <v-container fluid>
        <clinics-table></clinics-table>
        <add-clinic></add-clinic>
    </v-container>
</template>

<script>
    import AddClinic from "../components/Clinics/AddClinic";
    import ClinicsTable from "../components/Clinics/ClinicsTable";

    export default {
        name: "Clinics",
        components: {ClinicsTable, AddClinic},
    }
</script>

<style scoped>

</style>
