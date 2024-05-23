import { TestBed } from '@angular/core/testing';

import { ExampleInterceptorInterceptor } from './example-interceptor.interceptor';

describe('ExampleInterceptorInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      ExampleInterceptorInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: ExampleInterceptorInterceptor = TestBed.inject(ExampleInterceptorInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
