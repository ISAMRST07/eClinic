<template>
    <v-container fluid>
        <add-clinic-room/>
        <clinic-rooms-table/>
    </v-container>
</template>

<script>
    import ClinicRoomsTable from "../components/ClinicRooms/ClinicRoomsTable";
    import AddClinicRoom from "../components/ClinicRooms/AddClinicRoom";

    export default {
        name: "ClinicRooms",
        components: {AddClinicRoom, ClinicRoomsTable}
    }
</script>

<style scoped>

</style>
