import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/users/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  checkIfAdmin(user){
    if (user.isAdmin == "Y"){
      return "Sim";
    }else{
      return "Não";
    }
  }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getAll() {
    return this.http.get(API_URL);
  }

  get(id) {
    return this.http.get(`${API_URL}${id}`);
  }

  create(data) {
    return this.http.post(API_URL + 'create', data);
  }  

  update(id, data) {
    return this.http.put(`${API_URL}${id}`, data);
  }

  delete(id) {
    return this.http.delete(`${API_URL}${id}`);
  }


}
