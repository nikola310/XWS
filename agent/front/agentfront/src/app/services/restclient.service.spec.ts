import { TestBed, inject } from '@angular/core/testing';

import { RestclientService } from './restclient.service';

describe('RestclientService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RestclientService]
    });
  });

  it('should be created', inject([RestclientService], (service: RestclientService) => {
    expect(service).toBeTruthy();
  }));
});
