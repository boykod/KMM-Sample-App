//
//  ContentState.swift
//  iosApp
//
//  Created by Dmytro on 12.03.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Combine
import shared

final class ContentState: ObservableObject {
    
    init() {
        PlatformStore().runtime(render: render)
    }
    
    @Published var platform: PlatformStore.Props? = nil
    
    func render(
        props: PlatformStore.Props,
        dispatch: @escaping (PlatformStore.Msg) -> KotlinUnit
    ) {
        platform = props
    }
    
    func update(msg: PlatformStore.Msg) {
        render(props: platform!, dispatch: { (dispatch) in msg
            return.init()
        })
    }

}
