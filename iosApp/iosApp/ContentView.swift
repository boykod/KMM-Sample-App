import SwiftUI
import shared


struct ContentView: View {
    
    @ObservedObject var state: ContentState
    
    var body: some View {
        Text(state.platform?.platform ?? Greeting().greeting())
            .onTapGesture(perform: { state.update(msg: state.platform!.getPlatform()) })
    }
}
