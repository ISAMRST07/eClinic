<template>
    <div>
        <modify-intervention-type-dialog v-model="addDialog"/>
        <v-btn
                v-if="role === adminCode"
                fab
                dark
                large
                color="red"
                class="fab-br"
                @click.stop="addDialog = true"
        >
            <v-icon> mdi-plus </v-icon>
        </v-btn>
    </div>
</template>

<script>
    import ModifyInterventionTypeDialog from "./ModifyInterventionTypeDialog";
    import {ClinicalCenterAdmin} from "../../utils/DrawerItems";
    import {mapState} from "vuex";
    export default {
        name: "AddInterventionType",
        components: {ModifyInterventionTypeDialog},
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
