console.log("connected")

class Person{
	constructor(id, age, email){
		this.id = id;
		this.age=age;
		this.email=email;
		
	}
	toString(){
		return `{"id":"${this.id}", "age":${this.age}, "email":${this.email}}`;
	}
}
