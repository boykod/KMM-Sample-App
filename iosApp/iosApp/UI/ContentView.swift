import SwiftUI
import shared


struct ContentView: View {
    
    @ObservedObject var viewModel: ContentViewModel
    
    var body: some View {
//        Text(Greeting().greeting())
        Text(viewModel.userData)
    }
}

