import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetPassUrlComponent } from './reset-pass-url.component';

describe('ResetPassUrlComponent', () => {
  let component: ResetPassUrlComponent;
  let fixture: ComponentFixture<ResetPassUrlComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResetPassUrlComponent]
    });
    fixture = TestBed.createComponent(ResetPassUrlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
