import {redirectToLogin} from "../store/AuthModule";

export function defaultError(err) {
    if(err.response) {
        if(err.response.status === 401) {
            redirectToLogin(commit);
        }
    }
}
