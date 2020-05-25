<template>
    <v-container fluid>
        <clinics-table></clinics-table>
        <add-clinic
                v-if="role === adminCode"
        ></add-clinic>
    </v-container>
</template>

<script>
    import AddClinic from "../components/Clinics/AddClinic";
    import ClinicsTable from "../components/Clinics/ClinicsTable";
    import {mapState} from "vuex";
    import {ClinicalCenterAdmin} from "../utils/DrawerItems";

    export default {
        name: "Clinics",
        components: {ClinicsTable, AddClinic},
        data: () => ({
            adminCode: ClinicalCenterAdmin.code,
        }),
        computed: {
            ...mapState('auth', ['role']),
        }
    }
</script>

<style scoped>

</style>
