<template>
    <v-container fluid>
        <clinic-rooms-table/>
    </v-container>
</template>

<script>
    import ClinicRoomsTable from "../components/ClinicRooms/ClinicRoomsTable";
    export default {
        name: "ClinicRooms",
        components: {ClinicRoomsTable}
    }
</script>

<style scoped>

</style>
