<template>
    <v-app>
        <v-content>
            <v-container
                    class="fill-height"
                    fluid
            >
                <v-row
                        align="center"
                        justify="center"
                        class="mt-0"
                >
                    <v-col
                            cols="12"
                            sm="8"
                            md="4"
                    >
                        <v-card outlined>
                            <v-stepper v-model="step" class="elevation-0">
                                <v-stepper-items>
                                    <v-stepper-content step="1">
                                        <router-view/>
                                    </v-stepper-content>
                                    <v-stepper-content step="2">
                                    </v-stepper-content>
                                </v-stepper-items>
                            </v-stepper>
                        </v-card>
                    </v-col>
                </v-row>

            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import RegisterView from "../components/Auth/RegisterView";
    import LoginView from "../components/Auth/LoginView";

    export default {
        name: "Login",
        components: {RegisterView, LoginView},
        data: () => ({
            step: 1,
        }),
        watch: {
            step(val) {
                if (val === 1){
                    this.$router.push('/auth');
                } else {
                    this.$router.push('/auth/register');
                }
            },
            $route(to, from) {
                // if(to === '/auth') this.step = 1;
                // else if (to === '/auth/register') this.step = 2;
            }
        },
        created() {
        }
    }
</script>

<style scoped>

</style>
