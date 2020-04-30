<template>
    <v-container fluid>
        <table-clinic-administrator></table-clinic-administrator>
        <add-clinic-administrator></add-clinic-administrator>
    </v-container>
</template>

<script>

    import AddClinicAdministrator from "../components/ClinicAdministrator/AddClinicAdministrator";
    import TableClinicAdministrator from "../components/ClinicAdministrator/TableClinicAdministrator";

    export default {
        name: "ClinicAdministrators",
        components: {TableClinicAdministrator, AddClinicAdministrator},
    }
</script>

<style scoped>

</style>
