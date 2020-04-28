<template>
    <v-container fluid>
        <clinics-table></clinics-table>
        <add-clinic></add-clinic>
    </v-container>
</template>

<script>
    import AddClinic from "../components/AddClinic";
    import ClinicsTable from "../components/ClinicsTable";

    export default {
        name: "Clinics",
        components: {ClinicsTable, AddClinic},
    }
</script>

<style scoped>

</style>
