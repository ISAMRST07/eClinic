<template>
    <div>
<!--        <modify-clinic-dialog-->
<!--                v-model="addDialog"/>-->
        <v-btn
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
    export default {
        name: "AddClinicRoom",
        data: () => ({
            addDialog: false
        })
    }
</script>

<style scoped>

</style>
