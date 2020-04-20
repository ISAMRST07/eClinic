import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login";
import Main from "../views/Main";
import loggedRoutes from "./loggedRoutes";
Vue.use(VueRouter);

  const routes = [
    {
      path: '/',
      component: Main,
      children: loggedRoutes,
      meta: {
        public: false,
      }
    },
    {
      path: '/login',
      component: Login,
      meta: {
        public: true,
      }
    }

];

const router = new VueRouter({
  routes
});

router.beforeEach((to, from, next) => {
  // check if logged. if not, send them to login page no matter what
  let isPublic = to.matched.some((routeRecord) => routeRecord.meta.public);
  console.log(isPublic);
  next();
});

export default router
