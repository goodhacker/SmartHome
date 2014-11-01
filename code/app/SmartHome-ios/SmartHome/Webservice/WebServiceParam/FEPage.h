//
//  FEPage.h
//  SmartHome
//
//  Created by Seven on 14-11-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FERequestBaseData.h"

@interface FEPage : FERequestBaseData

@property (nonatomic, strong, readonly) NSNumber *pageSize;
@property (nonatomic, strong, readonly) NSNumber *currentPage;
@property (nonatomic, strong, readonly) NSNumber *count;

-(id)initWithPageSize:(NSInteger)pageSize currentPage:(NSInteger)page count:(NSInteger)count;

@end
