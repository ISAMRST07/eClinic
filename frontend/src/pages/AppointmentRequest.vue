<template>
    <v-container fluid>
        <appointment-requests-table></appointment-requests-table>
    </v-container>
</template>

<script>
    import DoctorTable from "../components/Doctor/DoctorTable";
    import AddDoctor from "../components/Doctor/AddDoctor";
    import AppointmentRequestsTable from "../components/AppointmentRequests/AppointmentRequestsTable";
    export default {
        name: "Doctor",
        components: {AppointmentRequestsTable, AddDoctor, DoctorTable}
    }
</script>

<style scoped>

</style>
