import {Injectable} from '@angular/core';
import {AuthRequest} from '../dtos/auth-request';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {tap} from 'rxjs/operators';
// @ts-ignore
import jwt_decode from 'jwt-decode';
import {Globals} from '../global/globals';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authBaseUri: string = this.globals.backendUri + '/authentication';

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  /**
   * Login in the user. If it was successful, a valid JWT token will be stored
   * @param authRequest User data
   */
  loginUser(authRequest: AuthRequest): Observable<string> {
    return this.httpClient.post(this.authBaseUri, authRequest, {responseType: 'text'})
      .pipe(
        tap((authResponse: string) => this.setToken(authResponse))
      );
  }


  /**
   * Check if a valid JWT token is saved in the localStorage
   */
  isLoggedIn() {
    return !!this.getToken() && (this.getTokenExpirationDate(this.getToken()).valueOf() > new Date().valueOf());
  }

  logoutUser() {
    console.log('Logout');
    localStorage.removeItem('authToken');
  }

  getToken() {
    if (localStorage.getItem('authToken') == null) {
      return '';
    }
    return localStorage.getItem('authToken');
  }

  /**
   * Returns the user role based on the current token
   */
  getUserRole() {
    if (this.getToken() != null) {
      const decoded: any = jwt_decode(this.getToken());
      const authInfo: string[] = decoded.rol;
      if (authInfo.includes('ROLE_ADMIN')) {
        return 'ADMIN';
      } else if (authInfo.includes('ROLE_EMPLOYER')) {
        return 'EMPLOYER';
      } else if (authInfo.includes('ROLE_EMPLOYEE')) {
        return 'EMPLOYEE';
      }
    }
    return 'UNDEFINED';
  }

  userIsEmployer(): boolean {
    return this.getUserRole() === 'EMPLOYER';
  }

  userIsEmployee(): boolean {
    return this.getUserRole() === 'EMPLOYEE';
  }

  /**
   * Returns the users email address based on the current token
   */
  getTokenIdentifier(): String {
    if (this.getToken() === '') { return null; }
    const decoded: any = jwt_decode(this.getToken());
    if (decoded.sub === undefined) {
      return null;
    }
    return decoded.sub;
  }

  private setToken(authResponse: string) {
    localStorage.setItem('authToken', authResponse);
  }

  private getTokenExpirationDate(token: string): Date {
    const decoded: any = jwt_decode(token);
    if (decoded.exp === undefined) {
      return null;
    }

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }
}
