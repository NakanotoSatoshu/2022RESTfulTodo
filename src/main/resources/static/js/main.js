const { createApp, ref, onMounted } = Vue;
const app = Vue.createApp({
    setup() {
        const users = ref([]);
        const name = ref('');
        //const test = ref({ user_id2: '',item_name2:'',});

        const createNewUser = () => {
          axios
            .post('https://jsonplaceholder.typicode.com/users', {
              name: name.value,
            })
            .then((response) => users.value.unshift(response.data))
            .catch((error) => console.log(error));
        };
        const thisistest = () => {
            axios
              .post('/...', {
                id: this.id,
                item_name: this.item_name,
              })
              .then((response) => users.value.unshift(response.data))
              .catch((error) => console.log(error));
          };
        const updateOne = () => {
          console.log("test");
        };
        onMounted(() => {
          axios
            .get('https://jsonplaceholder.typicode.com/users')
            .then((response) => (users.value = response.data))
            .catch((error) => console.log(error));
    });
    return {
      users,
      name,
      createNewUser,
    };
    },
    data() {
      return {
        todo ,
        user_id2: '',
        item_name2:'',
        user_id: '',
        item_name:'',
      }
    },
    methods: {
         updateOne:function(){
         console.log("GO!!!")
         const fData = new FormData();
         //let data = JSON.stringify(user_id2)
         console.log(this.user_id)
         //console.log(test.user_id2)
         //console.log(data)
         console.log(this.item_name)
         //console.log(test.item_name2)
         fData.append( 'user_id2' ,this.user_id);
         fData.append( 'item_name2' ,this.item_name);

         axios
         .post('/edit', fData, { headers: {'Content-Type':'application/json'} }  )
         .then((response) =>
                      (response.data))
                     // console.log(response.status)
                     // console.log(response.headers)
                     // console.log(response.statusText)
                     // console.log(response.config)
         .catch((error) =>
              console.log(error));
             // console.log(JSON.stringify(error));
            //  console.log(error.name);
             // console.log(error.message);
             // console.log(error.request);
            //  console.log(error.response);

         }
    }
})


