<template>
    <v-dialog
            :value="value"
            @input="$emit('input', $event)"
            @keydown.enter="$emit('close')"
            max-width="290"
    >
        <v-card>
            <v-card-title class="headline">{{ `Cancellation` ? mode === 'cancel' : 'Deletion'}}</v-card-title>

            <v-card-text>
                Are you sure that you want to {{ `cancel` ? mode === 'cancel' : 'deny' }} request with ID
                <span class="text--primary">{{ deleteName }}</span>?
            </v-card-text>

            <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn
                        ref="btCloseDelete"
                        color="green darken-1"
                        text
                        @click="$emit('close')"
                >
                    No
                </v-btn>

                <v-btn
                        color="green darken-1"
                        text
                        @click="$emit('delete')"
                >
                    Yes
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>

    export default {
        name: "DeleteDialog",
        props: ['value', 'mode', 'request'],
        computed: {
            deleteName() {
                return this.request ? this.request.id : '';
            }
        }

    }
</script>

<style scoped>

</style>
