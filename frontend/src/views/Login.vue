<template>
    <v-app id="inspire">
        <v-content>
            <v-container
                    class="fill-height"
                    fluid
            >
                <v-row
                        align="center"
                        justify="center"
                >
                    <v-col
                            cols="12"
                            sm="8"
                            md="4"
                    >
                        <v-card class="elevation-12">
                            <v-toolbar
                                    color="primary"
                                    dark
                                    flat
                            >
                                <v-toolbar-title>Login</v-toolbar-title>
                            </v-toolbar>
                            <v-card-text>
                                <v-form>
                                    <v-text-field
                                            label="Email"
                                            name="email"
                                            :rules="[rules.required, rules.email]"
                                            v-model="authRequest.email"
                                            prepend-icon="mdi-email"

                                    />

                                    <v-text-field
                                            id="password"
                                            label="Password"
                                            name="password"
                                            :rules="[rules.required]"
                                            v-model="authRequest.password"
                                            prepend-icon="mdi-lock"
                                            type="password"
                                    />
                                </v-form>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer />
                                <v-btn color="primary" @click="postLogin">
                                    <v-icon dense class="mr-1"> mdi-login-variant </v-icon>
                                    Login
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import {emailPattern} from "../utils/skeletons";
    import {mapActions, mapGetters, mapState} from "vuex";

    export default {
        name: "Login",
        data: () => ({
            authRequest: {
                email: null,
                password: null,
            },
            rules: {
                required: value => !!value || 'Required.',
                email: value => {
                    return emailPattern.test(value) || 'Invalid e-mail.';
                },
            }
        }),
        computed: {
            ...mapGetters('auth', ['loggedIn']),
        },
        watch: {
            loggedIn(val) {
                if (val) {
                    this.$router.push('/');
                }
            }
        },
        methods: {
            ...mapActions('auth', ['login']),
            postLogin(event) {
                this.login(this.authRequest);
            }
        }
    }
</script>

<style scoped>

</style>
