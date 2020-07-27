import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/users/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
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

  findByTitle(title) {
    return this.http.get(`${API_URL}?title=${title}`);
  }

}
