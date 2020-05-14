<template>
    <div>
        <modify-clinic-dialog
            v-model="addDialog"/>
        <v-btn
                v-if="role === adminCode"
                fab
                dark
                large
                color="red"
                class="fab-br"
                @click.stop="addDialog = true"
        >
            <v-icon> mdi-plus</v-icon>
        </v-btn>
    </div>
</template>

<script>
    import ModifyClinicDialog from "./ModifyClinicDialog";
    import {mapState} from "vuex";
    import {ClinicalCenterAdmin} from "../../utils/DrawerItems";

    export default {
        name: "AddClinic",
        components: {ModifyClinicDialog},
        data: () => ({
            addDialog: false,
            adminCode: ClinicalCenterAdmin.code
        }),
        computed: {
            ...mapState('auth', ['role']),
        }

    }
</script>

<style scoped>
</style>
