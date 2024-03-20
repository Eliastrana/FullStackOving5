
import router from '../../router';

// import store from '@/store'


export default async function sessionToken () {
    // const sessionToken = store.state.sessionToken
    const token = localStorage.getItem('authToken');

    if (sessionToken === null) {
        alert("Log in to access your profile!")
        await router.push("/login")
        throw new Error("Session token cannot be null. Login in again.")
    }

    return token
}