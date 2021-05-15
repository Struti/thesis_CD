import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AdminComponent } from './modules/admin/admin.component';
import { UserComponent } from './modules/user/user.component';
import { WelcomeComponent } from './modules/component/welcome/welcome.component';
import { HeaderComponent } from './modules/component/header/header.component';
import { ErrorPageComponent } from './modules/component/error-page/error-page.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { UserPageComponent } from './modules/component/user-page/user-page.component';
import { HolidayPageComponent } from './modules/component/holiday-page/holiday-page.component';
import { HolidayPlanPageComponent } from './modules/component/holiday-plan-page/holiday-plan-page.component';
import { AppRoutingModule } from './app-routing.module';


export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    UserComponent,
    WelcomeComponent,
    HeaderComponent,
    ErrorPageComponent,
    UserPageComponent,
    HolidayPageComponent,
    HolidayPlanPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FlexLayoutModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      },
    })
  ],
  exports: [TranslateModule],
  providers: [TranslateModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
