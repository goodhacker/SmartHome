//
//  FEWebServiceManager.h
//  SmartHome
//
//  Created by Seven on 14-10-18.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <AFNetworking/AFHTTPRequestOperationManager.h>
#import "FEDataUser.h"

@interface FEWebServiceManager : AFHTTPRequestOperationManager

//DEFINE_SINGLETON_FOR_HEADER(FEWebServiceManager);

+(FEWebServiceManager *)sharedInstance;

-(AFHTTPRequestOperation *)siginWithParam:(NSString *)param response:(void (^)(NSError *error,FEDataUser *user))block;

@end
