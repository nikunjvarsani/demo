import { TestBed } from '@angular/core/testing';

import { StudentinterceptorInterceptor } from './studentinterceptor.interceptor';

describe('StudentinterceptorInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      StudentinterceptorInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: StudentinterceptorInterceptor = TestBed.inject(StudentinterceptorInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
