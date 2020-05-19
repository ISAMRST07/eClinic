<template>
    <v-container fluid>
        <table-clinic-center-administrator/>
        <add-clinic-center-administrator/>
    </v-container>
</template>

<script>
    import AddClinicCenterAdministrator from "../components/ClinicCenterAdministrator/AddClinicCenterAdministrator";
    import TableClinicCenterAdministrator from "../components/ClinicCenterAdministrator/TableClinicCenterAdministrator";

    export default {
        name: "ClinicCenterAdministrators",
        components: {TableClinicCenterAdministrator, AddClinicCenterAdministrator},
    }
</script>

<style scoped>

</style>